package com.demo.sampleproject.datahandler;

/**
 * Created by kevinpanuelos on 11/16/13.
 */
public class GaaValues {

    public static enum APPRO_TYPE {
        NEW ("new_appro"),
        AUTO ("auto_appro");

        private final String param;

        private APPRO_TYPE (String parameter) {
            this.param = parameter;
        }

        public String getParam(){
            return this.param;
        }

    }

}
