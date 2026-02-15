import SwiftUI

private let loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel augue quis sapien congue faucibus ac sed enim. Phasellus fermentum interdum arcu, id consectetur dui laoreet vitae."

struct ContentView: View {
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 0) {
                ForEach(0 ..< 1000, id: \.self) { index in
                    ListItemView(index: index)
                }
            }
        }
        .background(.white)
        .preferredColorScheme(.light)
    }
}

struct ListItemView: View {
    let index: Int
    @State private var text = String(loremIpsum.prefix(Int.random(in: 1 ... loremIpsum.count)))
    @State private var rotation = 0.0

    var body: some View {
        HStack(alignment: .center, spacing: 16) {
            AsyncImage(url: URL(string: "https://placehold.co/256x256.png?text=\(index + 1)")) { image in
                image.resizable()
            } placeholder: {
                Color.clear
            }
            .frame(width: 48, height: 48)
            Text(text)
                .frame(maxWidth: .infinity, alignment: .leading)
            Rectangle()
                .fill(.black)
                .frame(width: 24, height: 24)
                .rotationEffect(.degrees(rotation))
                .onAppear {
                    withAnimation(.linear(duration: 1).repeatForever(autoreverses: false)) {
                        rotation = 360
                    }
                }
        }
        .padding(16)
    }
}
