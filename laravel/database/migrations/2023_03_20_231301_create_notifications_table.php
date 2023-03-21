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
        Schema::create('notifications', function (Blueprint $table) {
            $table->id();
            $table->foreignId("id_envoi");
            $table->foreign("id_envoi")->references("id")->on("users")->onDelete("cascade");
            $table->foreignId("id_recu");
            $table->foreign("id_recu")->references("id")->on("users")->onDelete("cascade");
            $table->text("message");
            $table->boolean("etat")->default(false);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('notifications');
    }
};
