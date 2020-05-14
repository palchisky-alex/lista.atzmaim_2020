/*
 *  Copyright 2019 Qameta Software OÜ
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import io.qameta.allure.attachment.AttachmentContent;
import io.qameta.allure.attachment.AttachmentData;
import io.qameta.allure.attachment.AttachmentRenderException;

/**
 * @param <T> the type of attachment data
 * @author charlie (Dmitry Baev).
 */
@SuppressWarnings("PMD.AvoidUncheckedExceptionsInSignatures")
public interface AttachmentRenderer<T extends AttachmentData> {

    AttachmentContent render(T attachmentData) throws AttachmentRenderException;

}
