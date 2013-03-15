package org.carrot2.labs.smartsprites;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Copyright 2012 Eric Axley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author ax6
 * @phase compile
 * @goal spritify
 */
public class SmartSpritesMojo extends AbstractMojo {

    /**
     * @parameter expression="${smartSprites.rootDirPath}" default-value="${basedir}/src/main/webapp"
     */
    private String rootDirPath;
    /**
     * @parameter expression="${smartSprites.outputDirPath}" default-value="${project.build.directory}/${project.artifactId}"
     */
    private String outputDirPath;
    /**
     * @parameter expression="${smartSprites.cssFiles}" default-value=""
     */
    private String cssFiles;
    /**
     * @parameter expression="${smartSprites.documentRootDirPath}" default-value=""
     */
    private String documentRootDirPath;
    /**
     * @parameter expression="${smartSprites.spritePngDepth}" default-value=""
     */
    private String spritePngDepth;
    /**
     * @parameter expression="${smartSprites.spritePngIe6}" default-value=""
     */
    private String spritePngIe6;
    /**
     * @parameter expression="${smartSprites.cssFileEncoding}" default-value=""
     */
    private String cssFileEncoding;
    /**
     * @parameter expression="${smartSprites.cssFileSuffix}" default-value=""
     */
    private String cssFileSuffix;
    /**
     * @parameter expression="${smartSprites.noCssFileSuffix}" default-value="false"
     */
    private String noCssFileSuffix;
    /**
     * @parameter expression="${smartSprites.logLevel}" default-value="WARN"
     */
    private String logLevel;

    @Override
    public void execute() throws MojoExecutionException {
        List<String> args = new LinkedList<String>();
        args.add("--root-dir-path");
        args.add(rootDirPath);

        args.add("--output-dir-path");
        args.add(outputDirPath);

        if (logLevel != null) {
            args.add("--log-level");
            args.add(logLevel);
        }

        if (cssFiles != null) {
            args.add("--css-files");
            args.add(cssFiles);
        }

        if("true".equals(noCssFileSuffix)) {
            args.add("--css-file-suffix");
            args.add("");
        } else {
            if (cssFileSuffix != null) {
                args.add("--css-file-suffix");
                args.add(cssFileSuffix);
            }            
        }

        if (cssFileEncoding != null) {
            args.add("--css-file-encoding");
            args.add(cssFileEncoding);
        }

        if (documentRootDirPath != null) {
            args.add("--document-root-dir-path");
            args.add(documentRootDirPath);
        }

        if (spritePngDepth != null) {
            args.add("--sprite-png-depth");
            args.add(spritePngDepth);
        }

        if (spritePngIe6 != null) {
            args.add("--sprite-png-ie6");
            args.add(spritePngIe6);
        }

        try {
            SmartSprites.main(args.toArray(new String[0]));
        } catch (IOException e) {
            throw new MojoExecutionException("Running SmartSprites", e);
        }
    }
}
