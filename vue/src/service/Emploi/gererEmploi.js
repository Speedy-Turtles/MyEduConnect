import axios from "axios";
import  "@/plugins/axios";
export default {
    getEmploi(){
        return axios.get("emploi/getemploi");
    }
}