/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2024 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import org.junit.jupiter.api.Test;

import static com.github.javaparser.utils.TestUtils.assertEqualsStringIgnoringEol;

public class Issue3441Test extends AbstractLexicalPreservingTest {

	@Test
	void test() {
		    considerCode( 
		    		"public class Foo {\n" +
		    	     "    void bar() {\n" +
		    	     "        stmt1(); // comment 1\n" +
		    	     "        stmt2(); // comment 2\n" +
		    	     "    }\n" +
		    	     "}");
		    String expected = 
		    		"public class Foo {\n" +
		   	    	"    void bar() {\n" +
		   	    	"        stmt2(); // comment 2\n" +
		   	    	"    }\n" +
		   	    	"}";
		    
		BlockStmt block = cu.findFirst(BlockStmt.class).get();
	    Statement stmt = block.getStatements().get(0);
	    
	    block.remove(stmt);
	    
	    assertEqualsStringIgnoringEol(expected, LexicalPreservingPrinter.print(cu));
	}
    
}
