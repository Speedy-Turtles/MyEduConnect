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
        Schema::create('votes', function (Blueprint $table) {
            $table->unsignedBigInteger("user_id");
            $table->unsignedBigInteger("vote_session_id");
            $table->foreign("user_id")->references('id')->on("users")->onDelete("cascade");
            $table->foreign("vote_session_id")->references('id')->on("vote_sessions")->onDelete("cascade");
            $table->foreignId("user_nominated_id");
            $table->foreign("user_nominated_id")->references("user_id")->on("user_nominateds")->onDelete("cascade");
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('votes');
    }
};
