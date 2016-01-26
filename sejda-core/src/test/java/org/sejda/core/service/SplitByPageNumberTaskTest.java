/*
 * Created on 03/ago/2011
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * 
 * This file is part of the Sejda source code
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sejda.core.service;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.sejda.model.output.ExistingOutputPolicy;
import org.sejda.model.parameter.SplitByPagesParameters;
import org.sejda.model.pdf.PdfVersion;

/**
 * @author Andrea Vacondio
 * 
 */
@Ignore
public abstract class SplitByPageNumberTaskTest extends BaseTaskTest<SplitByPagesParameters> {
    private SplitByPagesParameters parameters;

    private void setUpParameters() throws IOException {
        parameters = new SplitByPagesParameters();
        parameters.setCompress(true);
        parameters.setVersion(PdfVersion.VERSION_1_6);
        parameters.setExistingOutputPolicy(ExistingOutputPolicy.OVERWRITE);
        testContext.directoryOutputTo(parameters);
    }

    @Test
    public void burst() throws IOException {
        setUpParameters();
        parameters.setSource(shortInput());
        doTestBurst();
    }

    @Test
    public void burstEnc() throws IOException {
        setUpParameters();
        parameters.setSource(stronglyEncryptedInput());
        doTestBurst();
    }

    @Test
    public void burstOutline() throws IOException {
        setUpParameters();
        parameters.setSource(largeOutlineInput());
        parameters.addPage(1);
        parameters.addPage(2);
        parameters.addPage(3);
        execute(parameters);
        testContext.assertTaskCompleted();
        testContext.assertOutputSize(4);
    }

    public void doTestBurst() throws IOException {
        parameters.addPage(1);
        parameters.addPage(2);
        parameters.addPage(3);
        execute(parameters);
        testContext.assertTaskCompleted();
        testContext.assertOutputSize(4);
    }

    @Test
    public void even() throws IOException {
        setUpParameters();
        parameters.setSource(shortInput());
        doTestEven();
    }

    @Test
    public void evenEnc() throws IOException {
        setUpParameters();
        parameters.setSource(encryptedInput());
        doTestEven();
    }

    public void doTestEven() throws IOException {
        parameters.addPage(2);
        parameters.addPage(4);
        execute(parameters);
        testContext.assertTaskCompleted();
        testContext.assertOutputSize(2);
    }

    @Test
    public void odd() throws IOException {
        setUpParameters();
        parameters.setSource(shortInput());
        doTestOdd();
    }

    @Test
    public void oddEnc() throws IOException {
        setUpParameters();
        parameters.setSource(encryptedInput());
        doTestOdd();
    }

    public void doTestOdd() throws IOException {
        parameters.addPage(1);
        parameters.addPage(3);
        execute(parameters);
        testContext.assertTaskCompleted();
        testContext.assertOutputSize(3);
    }
}
