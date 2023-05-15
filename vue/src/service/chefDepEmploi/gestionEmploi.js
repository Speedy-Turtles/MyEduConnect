import axios from "axios";
import "@/plugins/axios";
export default {
    getEmploi(classe) {
        return axios.get("emploiChef/getemploi?class_id=" + classe);
    },
    getMatieres() {
        return axios.get("/matiere");
    },
    getSalle() {
        return axios.get("/salle");
    },
    storeSeance(seance) {
        return axios.post("/emploiChef/storeemploi", seance);
    }
}