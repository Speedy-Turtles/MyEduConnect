import axios from "axios";
import "@/plugins/axios";

export default {
    getAllClasses() {
        return axios.get("/classes");
    },
    ClassesBySpecialte(id) {
        return axios.get("classe/classesBySpecialite/" + id);
    },
    DeleteClasse(id) {
        return axios.delete("classe/delete/" + id);
    },
    AddClasse(classe) {
        return axios.post("classe/AddClasse", classe);
    },
    updateClasse(id, classe) {
        return axios.put("classe/updateClasse/" + id, classe);
    }
}