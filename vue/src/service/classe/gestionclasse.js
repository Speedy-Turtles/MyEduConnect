import axios from "axios";
import "@/plugins/axios";

export default {
    ClassesBySpecialte(id) {
        return axios.get("classe/classesBySpecialite/" + id);
    }
}