<template>
    <div>
        <navbar/>
        <navigationDrawer current-page="home"/>
        <div class="main_content">
            <statistiqueChef></statistiqueChef>
        </div>
    </div>
</template>
<script>
import navbar from '@/components/chef_departement/navbar.vue';
import navigationDrawer from '@/components/chef_departement/navigationDrawer.vue';
import statistiqueChef from '@/components/chef_departement/statistiqueChef.vue';

    export default{
        components:{
            navbar,
            navigationDrawer,
            statistiqueChef
        }
    }
</script>
<style scoped>
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