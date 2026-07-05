package com.su0h.Chores.services;

import com.su0h.Chores.entities.Metadata;
import com.su0h.Chores.entities.Person;
import com.su0h.Chores.entities.Task;
import com.su0h.Chores.entities.TaskAssignment;
import com.su0h.Chores.entities.TaskAssignmentResponse;
import com.su0h.Chores.entities.TaskAcknowledgeResponse;
import com.su0h.Chores.entities.TaskAssignmentNotFoundException;
import com.su0h.Chores.repositories.MetadataRepository;
import com.su0h.Chores.repositories.TaskAssignmentRepository;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskAssignmentServiceTest {

    @Mock
    private TaskAssignmentRepository taskAssignmentRepository;

    @Mock
    private MetadataRepository metadataRepository;

    @Mock
    private MetadataService metadataService;

    @InjectMocks
    private TaskAssignmentService taskAssignmentService;

    @Test
    void testFetchAllTaskAssignments_Success() {
        LocalDateTime lastModified = LocalDateTime.now();
        when(metadataService.getLastModifiedDate()).thenReturn(lastModified);

        Person p1 = new Person("Alice");
        Task t1 = new Task("Vacuum");
        TaskAssignment ta1 = new TaskAssignment(p1, t1);

        List<TaskAssignment> assignments = new ArrayList<>();
        assignments.add(ta1);

        when(taskAssignmentRepository.findAll()).thenReturn(assignments);

        TaskAssignmentResponse response = taskAssignmentService.fetchAllTaskAssignments();

        assertEquals(lastModified, response.getLastModified());
        assertEquals(1, response.getTaskAssignments().size());
        assertEquals("Alice", response.getTaskAssignments().get(0).getPersonName());
        assertEquals("Vacuum", response.getTaskAssignments().get(0).getTaskName());
    }

    @Test
    void testShiftTaskAssignments_Success() {
        LocalDateTime lastModified = LocalDateTime.now();
        when(metadataService.getLastModifiedDate()).thenReturn(lastModified);

        List<TaskAssignment> assignments = getTaskAssignments();

        when(taskAssignmentRepository.findAll()).thenReturn(assignments);

        TaskAssignmentResponse response = taskAssignmentService.shiftTaskAssignments();

        verify(taskAssignmentRepository, never()).deleteAll();
        verify(metadataRepository, times(1)).save(argThat(m -> m.getKey().equals(Metadata.Key.LAST_MODIFIED.name())));
        verify(taskAssignmentRepository).saveAll(assignments);

        List<TaskAssignmentResponse.SimplifiedTaskAssignment> result = response.getTaskAssignments();

        // Shift left by 1: [A, B, C] -> [C, A, B]
        assertEquals("Task C", result.get(0).getTaskName());
        assertEquals("Task A", result.get(1).getTaskName());
        assertEquals("Task B", result.get(2).getTaskName());

        for (TaskAssignment ta : assignments) {
            assertEquals(TaskAssignment.Status.PENDING, ta.getStatus());
        }
    }

    @Test
    void testBasicUnshiftTaskAssignments_Success() {
        LocalDateTime lastModified = LocalDateTime.now();
        when(metadataService.getLastModifiedDate()).thenReturn(lastModified);

        List<TaskAssignment> assignments = getTaskAssignments();

        when(taskAssignmentRepository.findAll()).thenReturn(assignments);

        TaskAssignmentResponse response = taskAssignmentService.basicUnshiftTaskAssignments();

        verify(taskAssignmentRepository, never()).deleteAll();
        verify(metadataRepository).save(argThat(m -> m.getKey().equals(Metadata.Key.LAST_MODIFIED.name())));
        verify(metadataRepository).save(argThat(m -> m.getKey().equals(Metadata.Key.LAST_UNSHIFTED.name())));
        verify(taskAssignmentRepository).saveAll(assignments);

        List<TaskAssignmentResponse.SimplifiedTaskAssignment> result = response.getTaskAssignments();

        // Unshift right by 1: [A, B, C] -> [B, C, A]
        assertEquals("Task B", result.get(0).getTaskName());
        assertEquals("Task C", result.get(1).getTaskName());
        assertEquals("Task A", result.get(2).getTaskName());

        for (TaskAssignment ta : assignments) {
            assertEquals(TaskAssignment.Status.PENDING, ta.getStatus());
        }
    }

    private static @NonNull List<TaskAssignment> getTaskAssignments() {
        Person p1 = new Person("Alice");
        Task t1 = new Task("Task A");
        TaskAssignment ta1 = new TaskAssignment(p1, t1);

        Person p2 = new Person("Bob");
        Task t2 = new Task("Task B");
        TaskAssignment ta2 = new TaskAssignment(p2, t2);

        Person p3 = new Person("Charlie");
        Task t3 = new Task("Task C");
        TaskAssignment ta3 = new TaskAssignment(p3, t3);

        List<TaskAssignment> assignments = new ArrayList<>();
        assignments.add(ta1);
        assignments.add(ta2);
        assignments.add(ta3);
        return assignments;
    }

    @Test
    void testGetAcknowledgeInfo_Success() {
        Person p = new Person("Alice");
        Task t = new Task("Task A");
        ReflectionTestUtils.setField(t, "id", 1L);
        TaskAssignment ta = new TaskAssignment(p, t);

        when(taskAssignmentRepository.findByTaskId(1L)).thenReturn(Optional.of(ta));

        TaskAcknowledgeResponse response = taskAssignmentService.getAcknowledgeInfo(1L);

        assertEquals(1L, response.getTaskId());
        assertEquals("Task A", response.getTaskName());
        assertEquals(TaskAssignment.Status.PENDING, response.getStatus());
    }

    @Test
    void testGetAcknowledgeInfo_NotFound() {
        when(taskAssignmentRepository.findByTaskId(1L)).thenReturn(Optional.empty());

        assertThrows(TaskAssignmentNotFoundException.class, () -> taskAssignmentService.getAcknowledgeInfo(1L));
    }

    @Test
    void testAcknowledgeTask_Success_PendingToDone() {
        Person p = new Person("Alice");
        Task t = new Task("Task A");
        ReflectionTestUtils.setField(t, "id", 1L);
        TaskAssignment ta = new TaskAssignment(p, t);

        when(taskAssignmentRepository.findByTaskId(1L)).thenReturn(Optional.of(ta));

        TaskAcknowledgeResponse response = taskAssignmentService.acknowledgeTask(1L);

        assertEquals(1L, response.getTaskId());
        assertEquals("Task A", response.getTaskName());
        assertEquals(TaskAssignment.Status.DONE, response.getStatus());
        verify(taskAssignmentRepository).save(ta);
    }

    @Test
    void testAcknowledgeTask_Success_AlreadyDone() {
        Person p = new Person("Alice");
        Task t = new Task("Task A");
        ReflectionTestUtils.setField(t, "id", 1L);
        TaskAssignment ta = new TaskAssignment(p, t);
        ta.setStatus(TaskAssignment.Status.DONE);

        when(taskAssignmentRepository.findByTaskId(1L)).thenReturn(Optional.of(ta));

        TaskAcknowledgeResponse response = taskAssignmentService.acknowledgeTask(1L);

        assertEquals(1L, response.getTaskId());
        assertEquals("Task A", response.getTaskName());
        assertEquals(TaskAssignment.Status.DONE, response.getStatus());
        verify(taskAssignmentRepository, never()).save(ta);
    }

    @Test
    void testAcknowledgeTask_NotFound() {
        when(taskAssignmentRepository.findByTaskId(1L)).thenReturn(Optional.empty());

        assertThrows(TaskAssignmentNotFoundException.class, () -> taskAssignmentService.acknowledgeTask(1L));
    }

    @Test
    void testShiftTaskAssignments_ResetsStatusToPending() {
        LocalDateTime lastModified = LocalDateTime.now();
        when(metadataService.getLastModifiedDate()).thenReturn(lastModified);

        List<TaskAssignment> assignments = getTaskAssignments();
        // Simulate assignments that were marked DONE before this rotation
        for (TaskAssignment ta : assignments) {
            ta.setStatus(TaskAssignment.Status.DONE);
        }

        when(taskAssignmentRepository.findAll()).thenReturn(assignments);

        taskAssignmentService.shiftTaskAssignments();

        for (TaskAssignment ta : assignments) {
            assertEquals(TaskAssignment.Status.PENDING, ta.getStatus());
        }
    }

    @Test
    void testBasicUnshiftTaskAssignments_ResetsStatusToPending() {
        LocalDateTime lastModified = LocalDateTime.now();
        when(metadataService.getLastModifiedDate()).thenReturn(lastModified);

        List<TaskAssignment> assignments = getTaskAssignments();
        // Simulate assignments that were marked DONE before this rotation
        for (TaskAssignment ta : assignments) {
            ta.setStatus(TaskAssignment.Status.DONE);
        }

        when(taskAssignmentRepository.findAll()).thenReturn(assignments);

        taskAssignmentService.basicUnshiftTaskAssignments();

        for (TaskAssignment ta : assignments) {
            assertEquals(TaskAssignment.Status.PENDING, ta.getStatus());
        }
    }

    @Test
    void testPerformDailyScheduledShifting_AlwaysShifts() {
        TaskAssignmentService spyService = spy(taskAssignmentService);
        doReturn(new TaskAssignmentResponse(LocalDateTime.now(), new ArrayList<>()))
                .when(spyService).shiftTaskAssignments();

        spyService.performDailyScheduledShifting();

        verify(spyService).shiftTaskAssignments();
    }

    @Test
    void testPerformSecondScheduledShifting_ShiftsWhenActivityDetected() {
        when(taskAssignmentRepository.existsByStatus(TaskAssignment.Status.DONE)).thenReturn(true);

        TaskAssignmentService spyService = spy(taskAssignmentService);
        doReturn(new TaskAssignmentResponse(LocalDateTime.now(), new ArrayList<>()))
                .when(spyService).shiftTaskAssignments();

        spyService.performSecondScheduledShifting();

        verify(spyService).shiftTaskAssignments();
    }

    @Test
    void testPerformSecondScheduledShifting_SkipsWhenNoActivity() {
        when(taskAssignmentRepository.existsByStatus(TaskAssignment.Status.DONE)).thenReturn(false);

        TaskAssignmentService spyService = spy(taskAssignmentService);

        spyService.performSecondScheduledShifting();

        verify(spyService, never()).shiftTaskAssignments();
    }
}