import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Button} from "primeng/button";
import {CardModule} from "primeng/card";
import {CurrencyPipe} from "@angular/common";
import {InputNumberModule} from "primeng/inputnumber";
import {OverlayPanelModule} from "primeng/overlaypanel";
import {PrimeTemplate} from "primeng/api";
import {Product} from "../../data-access/product.model";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-product-view',
  standalone: true,
  imports: [
    Button,
    CardModule,
    CurrencyPipe,
    InputNumberModule,
    OverlayPanelModule,
    PrimeTemplate,
    FormsModule
  ],
  templateUrl: './product-view.component.html',
  styleUrl: './product-view.component.css'
})
export class ProductViewComponent {
  @Input() product!: Product;
  @Input() badgeValue!: string;
  @Input() productQuantityInCart!: number;
  @Input() showFooter: boolean = true;

  @Output() onCartQtyChange: EventEmitter<number> = new EventEmitter<number>();
  @Output() onUpdate: EventEmitter<Product> = new EventEmitter<Product>();
  @Output() onDelete: EventEmitter<Product> = new EventEmitter<Product>();


  onQtyChange(event: any) {
    this.onCartQtyChange.emit(event.value);
  }

  onUpdateProduct(product: Product) {
    this.onUpdate.emit(product);
  }
  onDeleteProduct(product: Product) {
    this.onDelete.emit(product);
  }
}
