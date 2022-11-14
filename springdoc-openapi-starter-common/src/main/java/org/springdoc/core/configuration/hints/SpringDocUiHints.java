/*
 *
 *  *
 *  *  *
 *  *  *  *
 *  *  *  *  * Copyright 2019-2022 the original author or authors.
 *  *  *  *  *
 *  *  *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  *  *  * you may not use this file except in compliance with the License.
 *  *  *  *  * You may obtain a copy of the License at
 *  *  *  *  *
 *  *  *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *  *  *
 *  *  *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  *  *  * See the License for the specific language governing permissions and
 *  *  *  *  * limitations under the License.
 *  *  *  *
 *  *  *
 *  *
 *
 */

package org.springdoc.core.configuration.hints;

import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

/**
 * The type Spring doc UI hints.
 * @author bnasslahsen
 */
public class SpringDocUiHints implements RuntimeHintsRegistrar {

	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
		//Classgraph
		ClassLoaderHandlerRegistry.CLASS_LOADER_HANDLERS.forEach(classLoaderHandlerRegistryEntry ->
			hints.reflection()
					.registerTypeIfPresent(classLoader, classLoaderHandlerRegistryEntry.classLoaderHandlerClass.getCanonicalName(),
							hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_METHODS)));
		hints.reflection()
				.registerTypeIfPresent(classLoader, ClassLoaderHandlerRegistry.FALLBACK_HANDLER.classLoaderHandlerClass.getCanonicalName(),
						hint -> hint.withMembers(MemberCategory.INVOKE_DECLARED_METHODS));
	}

}
