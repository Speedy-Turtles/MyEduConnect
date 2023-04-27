import axios from "axios";
import "@/plugins/axios";

export default {
    getAllDocment() {
        return axios.get('/documents/');
    },

    addDocument(doc) {
        return axios.post('/documents/addDemande',doc);
    },

    getAlldemande() {
        return axios.get("/documents/getAlldemande");
    },

    AccepterDocument(doc) {
        return axios.post("/documents/AccepterDocument", doc);
    },

    initailiser_demande(doc) {
        return axios.post("/documents/initailiser_demande", doc);
    },
    getChefAllDepartment() {
        return axios.get("/documents/demandes/pending");
    },
    AccepterChefDocument(doc) {
        return axios.put("/documents/demandes/AccepterDocument", doc);
    },
    ReffuserChefDoccument(doc) {
        return axios.put("/documents/demandes/AccepterDocument", doc);
    },
}