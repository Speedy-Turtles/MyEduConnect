<?php

namespace App\Notifications\EditProfil;

use Illuminate\Bus\Queueable;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Notifications\Messages\MailMessage;
use Illuminate\Notifications\Notification;

class SendChangerEmail extends Notification
{
    use Queueable;

    /**
     * Create a new notification instance.
     */
    public $email_new;
    public function __construct(String $email)
    {
        $this->email_new=$email;
    }

    /**
     * Get the notification's delivery channels.
     *
     * @return array<int, string>
     */
    public function via(object $notifiable): array
    {
        return ['mail'];
    }

    /**
     * Get the mail representation of the notification.
     */
    public function toMail(object $notifiable): MailMessage
    {
        $link=env("APP_FRONTEND")."/signin?email_current=";
        $link2="&email_new=".$this->email_new;
        return (new MailMessage)
                    ->subject('Clique for edit your email')
                    ->line('Thank you for using our application!')
                    ->action('Change Email', $link.$notifiable['email'].$link2);
    }

    /**
     * Get the array representation of the notification.
     *
     * @return array<string, mixed>
     */
    public function toArray(object $notifiable): array
    {
        return [
            //
        ];
    }
}
