# -*- coding: utf-8 -*-

from tkinter import *
from PIL import ImageTk, Image
from tkinter import messagebox
from tkinter.ttk import Combobox
from Conexion import Conexion
import pyodbc

class Login:
    def __init__(self, window, title, w, h):

        self.w = w
        self.h = h
        self.x = int(window.winfo_screenwidth() / 2 - self.w / 2)
        self.y = int(window.winfo_screenheight() / 2 - self.h / 2)
        self.user = "basededatosm6"
        self.passw = "basededatosm6"

        self.window = window
        self.window.title(title)
        self.window.geometry(f"{self.w}x{self.h}+{self.x}+{self.y}")
        self.window.resizable(False, False)
        self.window.iconbitmap('images/logo.ico')
        self.window.configure(background = 'white')

        self.img_emp = ImageTk.PhotoImage(Image.open("images/San-Roman-Logo.png"))
        self.img_empLabel = Label(self.window, image = self.img_emp,
                                  background = "white", borderwidth = 0, highlightthickness = 0)
        self.img_empLabel.place(x = 50, y = 70)

        self.tira1 = ImageTk.PhotoImage(Image.open("images/tira.png"))
        self.img_tira1 = Label(self.window, image = self.tira1,
                                  background = "white", borderwidth = 0, highlightthickness = 0)
        self.img_tira1.place(x = 0, y = 430)

        self.tira2 = ImageTk.PhotoImage(Image.open("images/tira2.png"))
        self.img_tira2 = Label(self.window, image = self.tira2,
                                  background = "white", borderwidth = 0, highlightthickness = 0)
        self.img_tira2.place(x = 0, y = 0)
        
        self.nombre = Label(self.window, text = "Nombre", font = ("Tahoma", 14),
                            background = "white", foreground = "#32cbb9")
        self.nombre.place(x = 100, y = 260)
        self.nombreIn = Entry(self.window, width = 25, font = ("Tahoma", 12),
                              background = "#f4f4f4", foreground = "#32cbb9")
        self.nombreIn.place(x = 210, y = 265)
        self.nombreIn.bind("<Key>", self.key)
        self.nombreIn.bind("<FocusIn>", self.focus)
        self.nombreIn.bind("<FocusOut>", self.sinfocus)
        
        self.password = Label(self.window, text = "Contraseña", font = ("Tahoma", 14),
                              background = "white", foreground = "#32cbb9")
        self.password.place(x = 100, y = 300)
        self.passwordIn = Entry(self.window, show = "•", width = 25, font = ("Tahoma", 12),
                                background = "#f4f4f4", foreground = "#32cbb9")
        self.passwordIn.place(x = 210, y = 305)
        self.passwordIn.bind("<Key>", self.key)
        self.passwordIn.bind("<FocusIn>", self.focus)
        self.passwordIn.bind("<FocusOut>", self.sinfocus)

        self.info = Label(self.window, text = "San Felipe No. 2599 Col. San Jorge, Monterrey, N.L. Tels: 818 708-4664, 83-11-2331",
                          font = ("Tahoma", 12), background = "#32cbb9", foreground = "white")
        self.info.place(x = 20, y = 20)

        self.cop = Label(self.window, text = "Cristalería San Román. © Copyright 2019. Todos los derechos reservados.",
                            font = ("Tahoma", 12), background = "black", foreground = "white")
        self.cop.place(x = 20, y = 450)
        
        self.ingresar = Button(self.window, text = "Ingresar", command = self.ingreso, font = ("Tahoma", 12),
                            background = "#32cbb9", foreground = "white", width = 8, borderwidth = 2, highlightthickness = 1,
                            activeforeground = "#32cbb9", activebackground = "white")
        self.ingresar.place(x = 280, y = 370)
        self.ingresar.bind("<Key>", self.key)
        self.ingresar.bind("<FocusIn>", self.focus)
        self.ingresar.bind("<FocusOut>", self.sinfocus)

    def ingreso(self):
        if self.nombreIn.get().strip() != "" and self.passwordIn.get() != "":
            if self.user == self.nombreIn.get().strip() and self.passw == self.passwordIn.get():
                messagebox.showinfo("Bienvenida", f"Bienvenido {self.nombreIn.get()}")
                self.window.destroy()
                window = Tk()
                regProd = RegistrarProducto(window, "Registrar producto", 650, 500)
                window.mainloop()
            else:
                messagebox.showinfo("Error", "Usuario o contraseña incorrectos.")
        else:
            messagebox.showinfo("Error", f"Se deben llenar todos los campos")

    def key(self, event):
        if event.char == "\r":
            if self.nombreIn.get().strip() != "" and self.passwordIn.get() != "":
                if self.user == self.nombreIn.get().strip() and self.passw == self.passwordIn.get():
                    messagebox.showinfo("Bienvenida", f"Bienvenido {self.nombreIn.get()}")
                    self.window.destroy()
                    window = Tk()
                    regProd = RegistrarProducto(window, "Registrar producto", 650, 500)
                    window.mainloop()
                else:
                    messagebox.showinfo("Error", "Usuario o contraseña incorrectos.")
            else:
                messagebox.showinfo("Error", f"Se deben llenar todos los campos")
        else:
            pass

    def focus(self, event):
        if event.widget == self.ingresar:
            self.ingresar.configure(state = "active")
        else:
            event.widget.configure(background = "#32cbb9", foreground = "white")

    def sinfocus(self, event):
        if event.widget == self.ingresar:
            self.ingresar.configure(state = "normal")
        else:
            event.widget.configure(background = "white", foreground = "#32cbb9")

class RegistrarProducto:
    def __init__(self, window, title, w, h):

        self.w = w
        self.h = h
        self.x = int(window.winfo_screenwidth() / 2 - self.w / 2)
        self.y = int(window.winfo_screenheight() / 2 - self.h / 2)

        self.db = Conexion()

        try:
            self.db.conectar()
        except:
            print("Error al conectar")
        
        self.window = window
        self.window.title(title)
        self.window.geometry(f"{self.w}x{self.h}+{self.x}+{self.y}")
        self.window.resizable(False, False)
        self.window.iconbitmap('images/logo.ico')
        self.window.configure(background = 'white')

        self.tira2 = ImageTk.PhotoImage(Image.open("images/tira2.png"))
        self.img_tira2 = Label(self.window, image = self.tira2,
                                  background = "white", borderwidth = 0, highlightthickness = 0)
        self.img_tira2.place(x = 0, y = 0)

        self.titulo = Label(self.window, text = "Registrar producto", font = ("Tahoma", 18),
                            background = "#32cbb9", foreground = "#ffffff")
        self.titulo.place(x = 15, y = 15)

        self.id_producto = Label(self.window, text = "Código de producto", font = ("Tahoma", 14),
                            background = "white", foreground = "#32cbb9")
        self.id_producto.place(x = 30, y = 120)

        consulta_id = [x for i in self.db.cursor.execute("SELECT MAX(co_prod) FROM Producto").fetchall() for x in i][0] #id max
        if consulta_id == None: #si no hay productos en la tabla
            id_nueva = 0
        else:
            id_nueva = consulta_id + 1

        self.id_productoIn = Entry(self.window, width = 5, font = ("Tahoma", 12),
                              background = "#e3e3e3", foreground = "#32cbb9", 
                              disabledbackground = "#e3e3e3", disabledforeground = "#32cbb9")                       
        self.id_productoIn.place(x = 220, y = 125)

        self.id_productoIn.insert(0, id_nueva)

        self.id_productoIn.configure(state = "disable")

        self.nom_producto = Label(self.window, text = "Nombre", font = ("Tahoma", 14),
                            background = "white", foreground = "#32cbb9")
        self.nom_producto.place(x = 310, y = 120)
        self.nom_productoIn = Entry(self.window, width = 20, font = ("Tahoma", 12),
                              background = "#f4f4f4", foreground = "#32cbb9")
        self.nom_productoIn.place(x = 400, y = 125)
        self.nom_productoIn.bind("<FocusIn>", self.focus)
        self.nom_productoIn.bind("<FocusOut>", self.sinfocus)

        #agregar combobox para categoria
        categorias = [x for i in self.db.cursor.execute("SELECT DISTINCT cat_prod FROM Producto").fetchall() for x in i]

        self.cat_producto = Label(self.window, text = "Categoría", font = ("Tahoma", 14),
                            background = "white", foreground = "#32cbb9")
        self.cat_producto.place(x = 30, y = 170)

        self.cat_comb = Combobox(self.window, values = categorias, state = "readonly", font = ("Tahoma", 12),
                                    background = "#f4f4f4", foreground = "#32cbb9", widt = 12)
        self.cat_comb.current(0)
        self.cat_comb.place(x = 130, y = 175)
        self.cat_comb.selection_clear()

        self.pre_producto = Label(self.window, text = "Precio", font = ("Tahoma", 14),
                            background = "white", foreground = "#32cbb9")
        self.pre_producto.place(x = 310, y = 170)
        self.pre_productoIn = Entry(self.window, width = 14, font = ("Tahoma", 12),
                              background = "#f4f4f4", foreground = "#32cbb9")
        self.pre_productoIn.place(x = 380, y = 175)

        self.tira1 = ImageTk.PhotoImage(Image.open("images/tira.png"))
        self.img_tira1 = Label(self.window, image = self.tira1,
                                  background = "white", borderwidth = 0, highlightthickness = 0)
        self.img_tira1.place(x = 0, y = 430)

        self.cerrar = Button(self.window, text = "Cerrar sesión", command = self.cerrarSesion, font = ("Tahoma", 12),
                            background = "#32cbb9", foreground = "white", width = 10, borderwidth = 2, highlightthickness = 1,
                            activeforeground = "#32cbb9", activebackground = "white")
        self.cerrar.place(x = 140, y = 370)
        self.cerrar.bind("<Key>", self.key)
        self.cerrar.bind("<FocusIn>", self.focus)
        self.cerrar.bind("<FocusOut>", self.sinfocus)

        self.ingresar = Button(self.window, text = "Ingresar", command = self.ingresarProducto, font = ("Tahoma", 12),
                            background = "#32cbb9", foreground = "white", width = 10, borderwidth = 2, highlightthickness = 1,
                            activeforeground = "#32cbb9", activebackground = "white")
        self.ingresar.place(x = 280, y = 370)
        self.ingresar.bind("<Key>", self.key)
        self.ingresar.bind("<FocusIn>", self.focus)
        self.ingresar.bind("<FocusOut>", self.sinfocus)

        self.buscar = Button(self.window, text = "buscar", command = self.buscarProducto, font = ("Tahoma", 12),
                            background = "#32cbb9", foreground = "white", width = 10, borderwidth = 2, highlightthickness = 1,
                            activeforeground = "#32cbb9", activebackground = "white")
        self.buscar.place(x = 420, y = 370)
        self.buscar.bind("<Key>", self.key)
        self.buscar.bind("<FocusIn>", self.focus)
        self.buscar.bind("<FocusOut>", self.sinfocus)

    def cerrarSesion(self):
        self.db.desconectar()
        self.window.destroy()
        window = Tk()
        login = Login(window, "Iniciar sesión", 650, 500)         
        window.mainloop()

    def buscarProducto(self):
        self.nom_productoIn.delete(0, END)
        self.pre_productoIn.delete(0, END)
        categoria = self.cat_comb.get()
        self.db.cursor.execute(f"SELECT * FROM Producto WHERE cat_prod = '{categoria}'")
        resultados = [list(i) for i in self.db.cursor]
        for i in resultados:
            print(i)

    def ingresarProducto(self):
        if self.nom_productoIn.get() != "" and self.pre_productoIn.get() != "":
            try:
                precio = float(self.pre_productoIn.get())
                consulta_id = [x for i in self.db.cursor.execute("SELECT MAX(co_prod) FROM Producto").fetchall() for x in i][0] #id max
                if consulta_id == None: #si no hay productos en la tabla
                    id_nueva = 0
                else:
                    id_nueva = consulta_id + 1
                self.db.cursor.execute(f"INSERT INTO Producto (co_prod, cat_prod, nom_prod, pre_prod) VALUES ('{id_nueva}', '{self.cat_comb.get()}', '{self.nom_productoIn.get()}', '{precio}')")
                self.db.cursor.commit()
                self.nom_productoIn.delete(0, END)
                self.pre_productoIn.delete(0, END)
                self.id_productoIn.configure(state = "normal")
                self.id_productoIn.delete(0, END)
                self.id_productoIn.insert(0, id_nueva + 1)
                self.id_productoIn.configure(state = "disable")
            except ValueError:
                print("Solo enteros.")
        else:
            pass
            

    def key(self, event):
        if event.char == "\r":
            if event.widget == self.buscar:
                self.buscarProducto()
            elif event.widget == self.ingresar:
                self.ingresarProducto()
            elif event.widget == self.cerrar:
                self.db.desconectar()
                self.window.destroy()
                window = Tk()
                login = Login(window, "Iniciar sesión", 650, 500)
                window.mainloop()
            elif event.widget == self.id_productoIn:
                self.buscarProducto()
            else:
                pass
    
    def focus(self, event):
        if event.widget == self.cerrar:
            self.cerrar.configure(state = "active")
        elif event.widget == self.buscar:
            self.buscar.configure(state = "active")
        elif event.widget == self.ingresar:
            self.ingresar.configure(state = "active")
        else:
            event.widget.configure(background = "#32cbb9", foreground = "white")

    def sinfocus(self, event):
        if event.widget == self.cerrar:
            self.cerrar.configure(state = "normal")
        elif event.widget == self.buscar:
            self.buscar.configure(state = "normal")
        elif event.widget == self.ingresar:
            self.ingresar.configure(state = "normal")
        else:
            event.widget.configure(background = "#f4f4f4", foreground = "#32cbb9")

if __name__ == "__main__":
    window = Tk()
    login = Login(window, "Iniciar sesión", 650, 500)
    window.mainloop()
