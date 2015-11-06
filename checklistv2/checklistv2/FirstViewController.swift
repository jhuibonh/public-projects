//
//  FirstViewController.swift
//  checklist
//
//  Created by JM Huibonhoa on 10/31/15.
//  Copyright © 2015 JM Huibonhoa. All rights reserved.
//

import UIKit
import CoreData


class FirstViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    @IBOutlet weak var checklistTable: UITableView!
    @IBOutlet var tableTasks: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //UITableViewEdit
    func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath){
        let appDel:AppDelegate = (UIApplication.sharedApplication().delegate as! AppDelegate)
        let context:NSManagedObjectContext = appDel.managedObjectContext

        if (editingStyle == UITableViewCellEditingStyle.Delete){
            context.deleteObject(taskManager.taskList[indexPath.row] as NSManagedObject)
            taskManager.taskList.removeAtIndex(indexPath.row)
            
            do {
                try context.save()
            } catch {
                print("Could not delete task")
            }
            tableTasks.reloadData()
        }
    }
    
    //UITableViewDataSource
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return taskManager.taskList.count
        
    }
    
    //return to view
    override func viewWillAppear(animated: Bool) {
        let appDel:AppDelegate = (UIApplication.sharedApplication().delegate as! AppDelegate)
        let context:NSManagedObjectContext = appDel.managedObjectContext
        
        let request = NSFetchRequest(entityName: "Tasks")
        request.returnsObjectsAsFaults = false;
        
        do{
            let results:NSArray = try context.executeFetchRequest(request)
            print(results)
            taskManager.taskList = results as! [NSManagedObject]
            
        } catch{
            print("error retrieving saved tasks")
        }
        print("reloading")
        tableTasks.reloadData();

    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell: UITableViewCell = UITableViewCell(style: UITableViewCellStyle.Subtitle,reuseIdentifier: "test")
        let task = taskManager.taskList[indexPath.row]
        
        cell.textLabel?.text = task.valueForKey("taskName") as? String
        cell.detailTextLabel?.text = task.valueForKey("taskDesc") as? String
        
        return cell
    }
    
    
}

