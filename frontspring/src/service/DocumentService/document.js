import Axios from "axios";

import "@/plugins/axios"

export default{
    getAllDocment(){
        return Axios.get("getDocuments");
    },

   
}