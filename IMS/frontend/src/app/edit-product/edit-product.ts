import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-edit-product',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './edit-product.html',
  styleUrls: ['./edit-product.css']
})
export class EditProductComponent implements OnInit {

  editForm!: FormGroup;
  productId!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.productId = Number(this.route.snapshot.paramMap.get('id'));

    this.editForm = this.fb.group({
      name: ['', Validators.required],
      brand: ['', Validators.required],
      size: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(1)]],
      quantity: ['', [Validators.required, Validators.min(1)]]
    });

    this.loadProduct(this.productId);
  }

  loadProduct(id: number) {
    this.productService.getById(id).subscribe({
      next: (data) => {
        this.editForm.patchValue(data);  // ⭐ Pre-fill form
      },
      error: (err) => {
        console.log(err);
        alert("Failed to load product");
      }
    });
  }

  onUpdate() {
    if (this.editForm.valid) {
      this.productService.updateProduct(this.productId, this.editForm.value).subscribe({
        next: () => {
          alert("Product updated successfully!");
          this.router.navigate(['/product']);
        },
        error: (err) => {
          console.log(err);
          alert("Failed to update product");
        }
      });
    }
  }
}