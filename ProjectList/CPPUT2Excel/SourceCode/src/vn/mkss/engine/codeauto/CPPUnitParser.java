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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.ExpansionOverlapsBoundaryException;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.gnu.cpp.GPPLanguage;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.model.ILanguage;
import org.eclipse.cdt.core.parser.DefaultLogService;
import org.eclipse.cdt.core.parser.FileContent;
import org.eclipse.cdt.core.parser.IParserLogService;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.cdt.core.parser.IncludeFileContentProvider;
import org.eclipse.cdt.core.parser.ScannerInfo;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTCompositeTypeSpecifier;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTName;
import org.eclipse.core.runtime.CoreException;

import rocky.common.Constant;
import rocky.common.FileUtil;

/*_
 * Parsing source code c++ to get information of
 * - Test cases
 * @author thachle
 */
public class CPPUnitParser extends BaseCPPVisitor {
    private final static Logger LOG = Logger.getLogger("CPPUnitParser");
    private List<String> lstIncludeSearchPath = new ArrayList<String>();

    public List<String> addIncludeSearchPath(String path) {
        lstIncludeSearchPath.add(path);
        return lstIncludeSearchPath;
    }
    public void parseFile(File file) {
        ILanguage lang = GPPLanguage.getDefault();
        char[] contents;
        String filePath = file.getAbsolutePath();

        Map<String, String> macroDefinitions = new HashMap<String, String>();
        String[] includeSearchPaths = lstIncludeSearchPath.toArray(Constant.BLANK_STRS);
        IScannerInfo si = new ScannerInfo(macroDefinitions, includeSearchPaths);
        IncludeFileContentProvider ifcp = IncludeFileContentProvider.getEmptyFilesProvider();

        IIndex idx = null;
        int option = ILanguage.OPTION_IS_SOURCE_UNIT;
        IParserLogService log = new DefaultLogService();

        try {
            contents = FileUtil.getContent(filePath, false, Constant.DEF_ENCODE).toCharArray();
            FileContent fileContent = FileContent.create(filePath, contents);
            // IScannerInfo

            IASTTranslationUnit ast = lang.getASTTranslationUnit(fileContent, si, ifcp, idx, option, log);

            // this class is a visitor
            ast.accept(this);

        } catch (CoreException ex) {
            LOG.error("Error parsing file '" + filePath + "'", ex);
        }

    }
    
    /**
     * Parse class declaration.
     * @param declSpec
     * @return
     * @see org.eclipse.cdt.core.dom.ast.ASTVisitor#visit(org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier)
     */
    @Override
    public int visit(IASTDeclSpecifier declSpec) {
        getLineNumber(declSpec);
        LOG.debug(lineNumber + ":declSpec=" + declSpec.getClass());
        // LOG.debug("declSpec=" + declSpec.getClass() + ";content=" + declSpec.getRawSignature());

        if (declSpec instanceof CPPASTCompositeTypeSpecifier) {
            CPPASTCompositeTypeSpecifier compType = (CPPASTCompositeTypeSpecifier) declSpec;
            String className = null;
            
            try {
                // Similar declSpec.getSyntax().toString()
                String kind = declSpec.getSyntax().getImage();
                if ("class".equals(kind)) { // Found class declaration
                    LOG.debug("compType.getContainingFilename():" + compType.getContainingFilename());
                    String firstLine = AppHelper.getFirstLine(compType.getRawSignature());

                    for (IASTNode node : compType.getChildren()) {
                        if (node instanceof CPPASTName) { // Class name
                            className = node.getSyntax().getImage();
                            processClassName(className);
                            
                        }
                        // LOG.debug("class=" + node.getClass() + ";getSyntax().getImage():" + node.getSyntax().getImage());
                    }
                }
            } catch (ExpansionOverlapsBoundaryException ex) {
                LOG.warn("Check class declaration", ex);
            }
            
        }
        return ASTVisitor.PROCESS_CONTINUE;
    }
    
    private void processClassName(String className) {
        LOG.debug("Found class name:" + className);    
    }
}
