import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './add-product.html',
  styleUrls: ['./add-product.css']
})
export class AddProductComponent {

  addProductForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router,
  ) {
    this.addProductForm = this.fb.group({
      name: ['', Validators.required],
      brand: ['', Validators.required],
      size: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(1)]],
      quantity: ['', [Validators.required, Validators.min(1)]]
    });
  }

 onSubmit() {
  if (this.addProductForm.valid) {

    this.productService.addProduct(this.addProductForm.value).subscribe({
      next: (res) => {
        alert('Product added successfully!');
        this.router.navigate(['/product']);   // 💥 Redirect to product page
      },
      error: (err) => {
        console.log(err);
        alert('Failed to add product');
      }
    });

  }
}

}
