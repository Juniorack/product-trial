import {Injectable, signal} from "@angular/core";
import {CartItem, Product} from "./product.model";

@Injectable({
  providedIn: "root"
})
export class CartService {
  private readonly _cart = signal<CartItem[]>([]);

  constructor() {
    this._cart.set(JSON.parse(localStorage.getItem("cart") || "[]"));
  }

  public getCart() {
    return this._cart.asReadonly();
  }

  public addToCart(product: Product, quantity?: number) {
    const index = this._cart().findIndex((item) => item.product.id === product.id);
    if (index !== -1) {
      this._cart.update(content => {
        content[index].quantity += quantity??1;
        localStorage.setItem("cart", JSON.stringify(content));
        return content;
      })
      return;
    }
    this._cart.update(content => {
      content.push({product, quantity: quantity??1});
      localStorage.setItem("cart", JSON.stringify(content));
      return content;
    });
  }

  public removeFromCart(product: Product, quantity?: number) {
    this._cart.update(content => {
      let cartContent = content.map((item) => {
        if (item.product.id === product.id) {
          item.quantity -= quantity??item.quantity;
        }
        return item;
      }).filter((item) => item.quantity > 0);
      localStorage.setItem("cart", JSON.stringify(cartContent));
      return cartContent;
    })
  }

  public clearCart() {
    this._cart.set([]);
    localStorage.setItem("cart", JSON.stringify(this._cart()));
  }
}
