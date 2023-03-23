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
        Schema::create('users', function (Blueprint $table) {
            $table->id();
            $table->string('LastName');
            $table->string('FirstName');
            $table->string('email')->unique();
            $table->timestamp('email_verified_at')->nullable();
            $table->string('password');
            $table->string('Photo');
            $table->integer('Cin');
            $table->enum("sex", ["Man", "Woman"]);
            $table->date("Birth_day");
            $table->integer('password_token')->nullable();
            $table->timestamp('password_token_send_at')->nullable();
            $table->foreignId("classe_id");
            $table->foreign("classe_id")->references("id")->on("classes")->onDelete("cascade");
            $table->rememberToken();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('users');
    }
};
