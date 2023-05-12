import Axios from "axios";

import "@/plugins/axios"

export default{
    getSpecialte(){
        return Axios.get("getSpecialte");
    }
}