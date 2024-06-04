import SwiftUI
import Firebase
import ComposeApp

@main
struct iOSApp: App {
    init(){
       FirebaseApp.configure()
       KoinIOS().initialize()
     }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
