/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package vn.mkss.engine.codeauto;

import org.apache.log4j.Logger;
import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.internal.core.dom.parser.ASTNode;
import org.eclipse.cdt.internal.core.dom.parser.ASTTranslationUnit;

/**
 * @author thachle
 */
public class BaseCPPVisitor extends ASTVisitor {
    private final static Logger LOG = Logger.getLogger("BaseVisitor");
    /** Line number of current node . */
    int lineNumber;

    /** Content of line at lineNumber . */
    String curLine;

    /** Previous line number. */
    int prevLineNumber = -1;

    /**
     * Default constructor: visit all nodes
     */
    public BaseCPPVisitor() {
        this.shouldVisitNames = true;
        this.shouldVisitDeclarations = true;
        this.shouldVisitInitializers = true;
        this.shouldVisitParameterDeclarations = true;
        this.shouldVisitDeclarators = true;
        this.shouldVisitDeclSpecifiers = true;
        this.shouldVisitArrayModifiers = true;
        this.shouldVisitPointerOperators = true;
        this.shouldVisitAttributes = true;
        this.shouldVisitTokens = true;
        this.shouldVisitExpressions = true;
        this.shouldVisitStatements = true;
        this.shouldVisitTypeIds = true;
        this.shouldVisitEnumerators = true;
        this.shouldVisitTranslationUnit = true;
        this.shouldVisitProblems = true;
        this.shouldVisitDesignators = true;
        this.shouldVisitBaseSpecifiers = true;
        this.shouldVisitNamespaces = true;
        this.shouldVisitTemplateParameters = true;
        this.shouldVisitCaptures = true;
        this.includeInactiveNodes = true;
        this.shouldVisitAmbiguousNodes = true;
        this.shouldVisitImplicitNames = true;
        this.shouldVisitImplicitNameAlternates = true;
        
    }

    /**
     * [Give the description for method].
     * @param node
     * @return
     */
    public char getCharBeforeNode(ASTNode node) {
        return node.getTranslationUnit().getRawSignature().charAt(node.getOffset() - 1);
    }
    

    /**
     * Update the class member lineNumber.
     * @param node
     * @return current line number of node
     */
    public int getLineNumber(IASTNode node) {
        if (node.getRawSignature().length() == 0) {
            // function(void)
            return lineNumber;
        }
        prevLineNumber = lineNumber;
        lineNumber = ASTTranslationUnit.getStartingLineNumber(node);

        /*
         * Get current line
         */
        if (lineNumber > prevLineNumber) {
            curLine = AppHelper.getFirstLine(node.getRawSignature());
        }

        return lineNumber;
    }
}
