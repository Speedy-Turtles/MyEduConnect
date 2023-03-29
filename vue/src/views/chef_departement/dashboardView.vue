<template>
    <div>
        <navbar/>
        <navigationDrawer/>
        <div class="main_content">
        <chart/>
        </div>
    </div>
</template>
<script>
import navbar from '@/components/chef_departement/navbar.vue';
import navigationDrawer from '@/components/chef_departement/navigationDrawer.vue';
import chart from '@/components/chef_departement/chart.vue';
    export default{
        components:{
            navbar,
            navigationDrawer,
            chart
        }
    }
</script>
<style>
.main_content{
    margin-left: 20%;
    margin-top: 5%;
}
</style>