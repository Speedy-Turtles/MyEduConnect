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
        Schema::create('classes', function (Blueprint $table) {
            $table->id();
            $table->String("nom");
            $table->foreignId("specialite_id");
            $table->foreign("specialite_id")->references("id")->on("specialites")->onDelete("cascade");
            $table->foreignId("emploi_id");
            $table->foreign("emploi_id")->references("id")->on("emplois")->onDelete("cascade");
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('classes');
    }
};
