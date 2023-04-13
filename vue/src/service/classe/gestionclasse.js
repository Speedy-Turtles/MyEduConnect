import axios from "axios";
import "@/plugins/axios";

export default {
    ClassesBySpecialte(id, status) {
        return axios.get("classe/classesBySpecialite/" + id, status);
    }
}