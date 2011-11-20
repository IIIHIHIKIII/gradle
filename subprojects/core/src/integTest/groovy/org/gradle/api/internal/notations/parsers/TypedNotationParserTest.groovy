/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.notations.parsers;


import spock.lang.Specification

/**
 * by Szczepan Faber, created at: 11/10/11
 */
public class TypedNotationParserTest extends Specification {

    def parser = new DummyParser();

    def "knows if can parse"(){
        expect:
        false == parser.canParse(new Object())
        true == parser.canParse("100")
    }

    def "throws meaningful exception on parse attempt"(){
        expect:
        100 == parser.parseNotation("100")

        when:
        parser.parseNotation(new Object())

        then:
        thrown(InvalidUserDataException)
    }

    class DummyParser extends TypedNotationParser<String, Integer> {

        DummyParser() {
            super(String.class)
        }

        Integer parseType(String notation) {
            return Integer.valueOf(notation);
        }
    }
}
