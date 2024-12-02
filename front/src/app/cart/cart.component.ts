import { Component } from '@angular/core';
import {CartService} from "../products/data-access/cart.service";
import {Product} from "../products/data-access/product.model";
import {Button} from "primeng/button";
import {InputNumberModule} from "primeng/inputnumber";
import {OverlayPanelModule} from "primeng/overlaypanel";
import {BadgeModule} from "primeng/badge";
import {ProductViewComponent} from "../products/ui/product-view/product-view.component";

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    Button,
    InputNumberModule,
    OverlayPanelModule,
    BadgeModule,
    ProductViewComponent
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  public readonly cartItems = this.cartService.getCart();

  constructor(private readonly cartService: CartService) {

  }

  private addToCart(product: Product, quantity?: number) {
    this.cartService.addToCart(product, quantity);
  }

  private removeFromCart(product: Product, quantity?: number) {
    this.cartService.removeFromCart(product, quantity);
  }
  public productQuantityInCart(product: Product) {
    return this.cartItems().find((item) => item.product.id === product.id)?.quantity ?? 0;
  }
  public badgeValue() {
    return '' + this.cartItems().length
  }

  public onCartQtyChange(quantity:number , product: Product) {
    const qty = this.productQuantityInCart(product);
    if (quantity === 0) {
      this.removeFromCart(product);
      return;
    }
    this.addToCart(product, (quantity - qty));
  }
}
