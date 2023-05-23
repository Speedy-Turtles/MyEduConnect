<template>
    <div>
        hello {{ id }}
    </div>
</template>
<script>
export default{
    created(){
        this.id=this.$router.param.id;
    },
    data(){
        return{
            id:0
        }
    },
    methods:{

    }
}
</script>