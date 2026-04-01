import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from '../product.service';
import { FormsModule } from '@angular/forms';

import { Router, RouterLink } from '@angular/router';
@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './product.html',
  styleUrls: ['./product.css']
})
export class ProductComponent implements OnInit {

  products: any[] = [];
  filteredProducts: any[] = [];
  searchText: string = "";
  sortKey: string = "id";

 constructor(private productService: ProductService, private router: Router) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getAll().subscribe({
      next: (data) => {
        console.log("PRODUCTS FROM BACKEND:", data);
        this.products = data;
        this.applyFilter();
        this.searchText = "";
        this.sortKey = "id";
      },
      error: (err) => {
        console.log("API ERROR:", err);
      }
    });
  }

  applyFilter() {
    let data = [...this.products];

    if (this.searchText.trim() !== "") {
      data = data.filter(p =>
        p.name.toLowerCase().includes(this.searchText.toLowerCase()) ||
        p.brand.toLowerCase().includes(this.searchText.toLowerCase()) ||
        p.size.toLowerCase().includes(this.searchText.toLowerCase())
      );
    }

    data.sort((a, b) => {
      if (this.sortKey === 'name') return a.name.localeCompare(b.name);
      if (this.sortKey === 'brand') return a.brand.localeCompare(b.brand);
      return a[this.sortKey] - b[this.sortKey];
    });

    this.filteredProducts = data;
  }

  onSearch() {
    this.applyFilter();
  }

  onSortChange() {
    this.applyFilter();
  }

  viewProduct(id: number) {
    alert("View product id: " + id);
  }

  
editProduct(id: number) {
  this.router.navigate(['/product/edit', id]);
}


  deleteProduct(id: number) {
    if (confirm("Are you sure?")) {
      this.productService.deleteProduct(id).subscribe(() => {
        this.loadProducts();
      });
    }
  }
}