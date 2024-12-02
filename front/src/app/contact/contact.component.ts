import { Component } from '@angular/core';
import {Button} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {InputNumberModule} from "primeng/inputnumber";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [
    Button,
    DropdownModule,
    FormsModule,
    InputNumberModule,
    InputTextModule,
    InputTextareaModule,
    ReactiveFormsModule,
    ToastModule
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {
  formGroup!: FormGroup;

  constructor(private fb: FormBuilder, private messageService: MessageService) {

  }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.formGroup = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      message: ['', [Validators.required, Validators.maxLength(300)]],
    });
  }

  onSubmit() {
    if (this.formGroup.invalid) {
      Object.keys(this.formGroup.controls).forEach(controlName =>
        this.formGroup.controls[controlName].markAsTouched()
      );
      this.messageService.add({ severity: 'warn', summary: 'Formulaire incomplet', detail: 'Complétez le formulaire !' });
      return;
    }
    this.messageService.add({ severity: 'success', summary: 'Message envoyé', detail: 'Demande de contact envoyée avec succès' });
    console.log(this.formGroup.getRawValue())
  }
}
