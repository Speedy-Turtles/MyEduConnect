import Axios from "axios";

import "@/plugins/axios"

export default{
    async chercherSpec(page,search,per){
        const res= await Axios.get(`getSepecialite?${search ? "search="+search : ""}&page=${page}&per_page=${per}`);
        return res;
    }
}