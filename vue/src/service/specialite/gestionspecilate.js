import axios from "axios";
import "@/plugins/axios";

export default {
    getSpecialte(page) {
        return axios.get("specialte/Allspecialte?page=" + page);
    },
    searchSpecialite($search, page) {
        return axios.get("specialte/Allspecialte?search=" + $search + "&page=" + page);
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