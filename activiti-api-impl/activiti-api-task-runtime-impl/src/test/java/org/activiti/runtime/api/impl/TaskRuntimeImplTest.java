/*
 * Copyright 2019 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.runtime.api.impl;

import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.model.impl.TaskImpl;
import org.activiti.api.task.model.payloads.UpdateTaskPayload;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskRuntimeImplTest {

    @InjectMocks
    private TaskRuntimeImpl taskRuntime;

    @Mock
    private TaskRuntimeHelper taskRuntimeHelper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void updateShouldReturnResultOfHelper() {
        //given
        UpdateTaskPayload updateTaskPayload = TaskPayloadBuilder
                .update()
                .withTaskId("taskId")
                .withDescription("new description")
                .build();

        TaskImpl updatedTask = new TaskImpl();
        given(taskRuntimeHelper.applyUpdateTaskPayload(false,
                                                       updateTaskPayload)).willReturn(updatedTask);

        //when
        Task retrievedTask = taskRuntime.update(updateTaskPayload);

        //then
        assertThat(retrievedTask).isEqualTo(updatedTask);
    }
}