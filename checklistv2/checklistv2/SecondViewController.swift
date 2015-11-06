//
//  SecondViewController.swift
//  checklist
//
//  Created by JM Huibonhoa on 10/31/15.
//  Copyright © 2015 JM Huibonhoa. All rights reserved.
//

import UIKit
import CoreData

class SecondViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet var txtTask: UITextField!
    @IBOutlet var txtDesc: UITextField!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    //Events
    
    @IBAction func save_task(){
        let appDel:AppDelegate = (UIApplication.sharedApplication().delegate as! AppDelegate)
        let context:NSManagedObjectContext = appDel.managedObjectContext
        
        let newTask = NSEntityDescription.insertNewObjectForEntityForName("Tasks", inManagedObjectContext: context) as NSManagedObject
        newTask.setValue(txtTask.text, forKey: "taskName")
        newTask.setValue(txtDesc.text, forKey:  "taskDesc")
        txtTask.text = ""
        txtDesc.text = ""

        
        do {
            try context.save()
            taskManager.taskList.append(newTask)
        } catch {
            print("Could not save task")
        }
        
        print(newTask)
        print("task saved")
        
        
    }
    
    
    
}

