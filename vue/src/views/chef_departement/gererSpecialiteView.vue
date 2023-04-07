<template>
    <div>
        <navbar/>
        <navigationDrawer current-page="specialite"/>
        <div class="main_content">
        
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
<style scoped>
.main_content{
    margin-left: 20%;
    margin-top: 5%;
}
</style>