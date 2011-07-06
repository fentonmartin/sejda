/*
 * Created on Jul 1, 2011
 * Copyright 2011 by Eduard Weissmann (edi.weissmann@gmail.com).
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.sejda.cli.adapters;

import java.io.File;

import org.sejda.core.manipulation.model.output.PdfDirectoryOutput;

/**
 * Adapter for {@link PdfDirectoryOutput}. Required due to missing String based constructor in the model object (see http://jewelcli.sourceforge.net/usage.html#Class)
 * 
 * @author Eduard Weissmann
 * 
 */
public class PdfDirectoryOutputAdapter {

    private final PdfDirectoryOutput pdfDirectoryOutput;

    public PdfDirectoryOutputAdapter(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            throw new IllegalArgumentException("Path '" + directoryPath + "' does not exist");
        }

        this.pdfDirectoryOutput = PdfDirectoryOutput.newInstance(new File(directoryPath));
    }

    /**
     * @return the pdfDirectoryOutput
     */
    public PdfDirectoryOutput getPdfDirectoryOutput() {
        return pdfDirectoryOutput;
    }
}
