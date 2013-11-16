package com.demo.sampleproject.datahandler;

import com.demo.sampleproject.R;

/**
 * Created by kevinpanuelos on 11/16/13.
 */
public class SaroValues {

    public static enum OBJECT_CODE {
        O_S7("S7", "Public Infrastructure"),
        O_49("49", "Repair and Maintenance -  Public Infrastructure");

        private final String OBJECT_CODE;
        private final String OBJECT_DESC;

        private OBJECT_CODE(String objCode, String objDesc) {
            this.OBJECT_CODE = objCode;
            this.OBJECT_DESC = objDesc;
        }

        public String getObjectCode() {
            return this.OBJECT_CODE;
        }

        public String getObjectDesc() {
            return this.OBJECT_DESC;
        }
    }

}
