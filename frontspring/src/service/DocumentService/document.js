import Axios from "axios";

import "@/plugins/axios"

export default{
    getAllDocment(){
        return Axios.get("getDocuments");
    },

    addDemande(id,langue){
        return Axios.post("addDemande?id=" + id + "&langue=" + langue)
    },
    async gererPdf(){
        return await Axios.get("getPdf",{
            responseType: 'blob'
            
        });
    },
    getDemande(id){
        return Axios.get("getDemande?id="+id);
    },
    getDemandeUser(){
        return Axios.get("getAllDemandes");
    },
    updateDemande(){
        return Axios.put("updateDemande");
    }
   
    

   
}