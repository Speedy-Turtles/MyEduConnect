<template>
    <div>
        <navbar/>
        <navigationDrawer current-page="home"/>
        <div class="main_content">
            <v-row>
                <v-col>
                <router-link to="">
                    
            <v-card elavation="4">
                <h3 text-center>review your proffesors</h3>
                <img src="@/../public/chef_departement/teacher.svg" height="300px" width="300px" alt="">
            </v-card>
        </router-link></v-col>
        <v-col>
                <router-link to="">
                    
            <v-card elavation="4">
                <h3>review your proffesors</h3>
                <img src="@/../public/chef_departement/teacher.svg" height="300px" width="300px" alt="">
            </v-card>
        </router-link></v-col>
        <v-col>
                <router-link to="">
                    
            <v-card elavation="4">
                <h3>review your proffesors</h3>
                <img src="@/../public/chef_departement/teacher.svg" height="300px" width="300px" alt="">
            </v-card>
        </router-link></v-col>
        </v-row>
        </div>
    </div>
</template>
<script>
import navbar from '@/components/chef_departement/navbar.vue';
import navigationDrawer from '@/components/chef_departement/navigationDrawer.vue';

    export default{
        components:{
            navbar,
            navigationDrawer
        }
    }
</script>
<style>
.main_content{
   margin-left: 20%!important;
   margin-top: 7%!important;
}
@media screen and (max-width:768px) {
    .main_content{
        margin-left: 5%!important;
        margin-top: 20%!important;
    }
}
</style>