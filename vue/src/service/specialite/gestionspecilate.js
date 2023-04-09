import axios from "axios";
import "@/plugins/axios";

export default {
    getSpecialte() {
        return axios.get("specialte/Allspecialte");
    },
    addSpecialite(specialite) {
        return axios.post("specialte/addSpecialite", specialite);
    }
}