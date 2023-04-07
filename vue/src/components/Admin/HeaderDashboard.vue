<template>
    <div  class="ma-5 mb-5">
        <v-container >
            <v-row>
              <h2>
                <span style="color: #5094df">{{
                  ViewCurrent.toUpperCase().substring(0, ViewCurrent.length - 3)
                }}</span
                >{{
                  ViewCurrent.toUpperCase().substring(
                    ViewCurrent.length,
                    ViewCurrent.length - 3
                  )
                }}
              </h2>
              <v-spacer></v-spacer>
              </v-row>
            
        </v-container>
        
    </div>
</template>

<script>

export default {
    props:{
         ViewCurrent:String
    },
};

</script>

<style scoped>
.chart_box{
    width: 500px;
    height: 200px;
}
.chart {
    height: 50vh;
  }
</style>