<?php

namespace Database\Seeders;

use App\Models\Role;
use App\Models\User;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Eloquent\Factories\Sequence;
use Illuminate\Database\Seeder;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        User::factory(10)->state(new Sequence(
            ["sex"=>"Man"],
            ["sex"=>"Woman"]
        ))->has(Role::factory(2)->state(new Sequence(
            ['Role_name'=>"etudiant"],
            ['Role_name'=>"ensignant"],
            ['Role_name'=>"Technicien"],
        )))->create();
    }
}
