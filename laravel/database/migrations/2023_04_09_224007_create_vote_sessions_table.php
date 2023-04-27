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
        Schema::create('vote_sessions', function (Blueprint $table) {
            $table->id();
            $table->String("Titre_Vote");
            $table->date("DateDebut");
            $table->date("DateFin");
            $table->Integer("etat")->default(1);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('vote_sessions');
    }
};
