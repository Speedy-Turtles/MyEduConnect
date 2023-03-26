<template>
    <div>
        <navbar/>
        <navigationDrawer/>
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
</style>