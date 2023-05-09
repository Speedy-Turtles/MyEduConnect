<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('seances', function (Blueprint $table) {
            $table->id();
            $table->integer('numero');
            $table->string('duree');
            $table->String('temp_debut');
            $table->String('temps_fin');
            $table->String('jour');

            $table->foreignId("emploi_id");
            $table->foreign("emploi_id")->references("id")->on("emplois")->onDelete("cascade");

            $table->foreignId("salle_id");
            $table->foreign("salle_id")->references('id')->on("salles")->onDelete("cascade");

            $table->foreignId("user_id");
            $table->foreign("user_id")->references('id')->on("users")->onDelete("cascade");

            $table->foreignId("matiere_id");
            $table->foreign("matiere_id")->references('id')->on("matieres")->onDelete("cascade");

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('seances');
    }
};
