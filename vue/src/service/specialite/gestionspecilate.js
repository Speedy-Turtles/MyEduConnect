import axios from "axios";
import "@/plugins/axios";

export default {
    getSpecialte() {
        return axios.get("specialte/Allspecialte");
    },
    addSpecialite(specialite) {
        return axios.post("specialte/addSpecialite", specialite);
    },
    deleteSpecialite(id) {
        return axios.delete("specialte/deleteSpecialite/" + id);
    },
    getSpecialiteByid(id) {
        return axios.get("specialte/getSpecialiteById/" + id);
    },
    updateSpecialite(id, specialite) {
        return axios.put("specialte/updateSpecialite/" + id, specialite);
    }
}