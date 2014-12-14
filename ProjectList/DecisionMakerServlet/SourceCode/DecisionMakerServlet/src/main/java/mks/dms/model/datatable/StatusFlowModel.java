/**
 * Licensed to Open-Ones under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones licenses this file to you under the Apache License,
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
package mks.dms.model.datatable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mks.dms.dao.entity.StatusFlow;
import mks.dms.util.AppCons.RESULT;
import mks.dms.util.AppUtil;

import org.apache.log4j.Logger;

/**
 * @author ThachLe
 */
public class StatusFlowModel extends AbstractTableObjectModel implements Serializable {
    private static final Logger LOG = Logger.getLogger(StatusFlowModel.class);

    public final static int IDX_ID = 0;

    public final static int IDX_REQUESTTYPECD = 1;

    public final static int IDX_TYPEUSER = 2;

    public final static int IDX_CURRENTSTATUS = 3;

    public final static int IDX_NEXTSTATUS = 4;

    /** Store result of saving. */
    public final static int IDX_RESULT = 5;

    public List<StatusFlow> getDataList() {
        List<StatusFlow> lstStatusFlow = null;
        if (data == null) {
            // Do nothing
        } else {
            lstStatusFlow = new ArrayList<StatusFlow>();
            Object[] row;
            StatusFlow statusFlow;

            for (Iterator<Object[]> itRow = data.iterator(); itRow.hasNext();) {
                row = itRow.next();

                statusFlow = new StatusFlow();
                try {
                    statusFlow.setId(Integer.valueOf(String.valueOf(row[IDX_ID])));
                } catch (NumberFormatException nfEx) {
                    LOG.warn("Could not parse number '" + row[IDX_ID] + "'", nfEx);
                }
                statusFlow.setRequesttypeCd(String.valueOf(row[IDX_REQUESTTYPECD]));
                statusFlow.setTypeUser(String.valueOf(row[IDX_TYPEUSER]));
                statusFlow.setCurrentStatus(String.valueOf(row[IDX_CURRENTSTATUS]));
                statusFlow.setNextStatus(String.valueOf(row[IDX_NEXTSTATUS]));

                lstStatusFlow.add(statusFlow);
            }
        }

        return lstStatusFlow;
    }

    public void setResultList(List<RESULT> lstResult) {
        if ((lstResult == null) || (this.data == null)) {
            return;
        } else {
            Object[] row;
            StatusFlow statusFlow;
            int i = 0;
            int numResult = lstResult.size();
            for (Iterator<Object[]> itRow = data.iterator(); itRow.hasNext(); i++) {
                row = itRow.next();

                statusFlow = new StatusFlow();
                statusFlow.setId(Integer.valueOf(String.valueOf(row[IDX_ID])));
                statusFlow.setRequesttypeCd(String.valueOf(row[IDX_REQUESTTYPECD]));
                statusFlow.setTypeUser(String.valueOf(row[IDX_TYPEUSER]));
                statusFlow.setCurrentStatus(String.valueOf(row[IDX_CURRENTSTATUS]));
                statusFlow.setNextStatus(String.valueOf(row[IDX_NEXTSTATUS]));

                if (i < numResult) {
                    row[IDX_RESULT] = lstResult.get(i).name();
                }
            }
        }
    }

    @Override
    public void setDataList(List<?> lstStatus) {
        Iterator itStatus = lstStatus.iterator();

        StatusFlow status;

        if (data == null) {
            data = new ArrayList<Object[]>(lstStatus.size());
        }

        Object[] arrObjs;
        while (itStatus.hasNext()) {
            status = (StatusFlow) itStatus.next();

            arrObjs = new Object[6];
            arrObjs[0] = AppUtil.formatJson(status.getId());
            arrObjs[1] = AppUtil.formatJson(status.getRequesttypeCd());
            arrObjs[2] = AppUtil.formatJson(status.getTypeUser());
            arrObjs[3] = AppUtil.formatJson(status.getCurrentStatus());
            arrObjs[4] = AppUtil.formatJson(status.getNextStatus());
            // Reserved column 6 for Result of saving

            data.add(arrObjs);
        }
    }
}
