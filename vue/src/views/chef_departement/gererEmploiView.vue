<template>
    <div>
        <navbar />
        <navigationDrawer :current-page="cureentPage" />
        <v-comtainer fluid>
            
        </v-comtainer>
    </div>
</template>
<script>
import navbar from '@/components/chef_departement/navbar.vue';
import navigationDrawer from '@/components/chef_departement/navigationDrawer.vue';
import gestionclasse from '@/service/classe/gestionclasse';
export default{
    name:'',
    components: {
        navbar,
        navigationDrawer
    },
    created(){
        this.getAllClasses();
    },
    data(){
        return{
            cureentPage:'emploi',
            classes:[]
        }
    },
    methods:{
        getAllClasses(){
            gestionclasse.getAllClasses().then(response=>{
                this.classes=response.data.data;
                console.log(this.classes);
            })
        },
    }
}
</script>